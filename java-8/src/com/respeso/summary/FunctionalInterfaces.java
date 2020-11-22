package com.respeso.summary;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.respeso.data.Account;
import com.respeso.data.Holder;
import com.respeso.data.factory.Factory;

/**
 * Java 8 Functional Interfaces. 
 *  
 * Consumer<>
 * BiConsumer<>
 * Predicate<>
 * BiPredicate<>
 * Function<>
 * BiFunction<>
 * UnaryOperator and BinaryOperation<>
 * Supplier<>
 * 
 * @author Rodrigo Espeso
 */
public class FunctionalInterfaces{
	
	/*
	 * Consumer<T>
	 * 
	 * Accepts a param and operate with it, but do not return nothing: implementation
	 * of a void method.
	 * 
	 */
	static Consumer<Holder> completeName = h -> System.out.print(h.getName() + " "+ h.getLastName()); 
	static Consumer<Holder> concatDetail = h -> System.out.print(" "+h+" is client of the bank"); 

	/*
	 * BiConsumer <T, U>
	 * 
	 * Accepts 2 params and operate with them, but do not return nothing: implementation
	 * of a void method.
	 */
	static BiConsumer<Holder, Account> holderInfo1 = (h, a) -> System.out.print("Holder ID :"+h.getId() +" - Account: " + a.getName() +". "); 
	static BiConsumer<Holder, Account> holderInfo2 = (h, a) -> System.out.print("Age: " +h.getAge() +" - Balance: " + a.getBalance()+"."); 

	/*
	 * Predicate<T>
	 * 
	 * Accepts a param an evaluate an expression using it. Implementation of a
	 * boolean return method.
	 */
	static Predicate<Account> isEuroAccount = a-> "EUR".equals(a.getCurrency());
	static Predicate<Account> isHealthyAccount = a-> a.getBalance()>30000L; // note how the lambda can be reduced
	
	/*
	 * BiPredicate<T, U>
	 * 
	 * Accepts 2 params an evaluate an expression using them. Implementation of a
	 * boolean return method.
	 */
	static BiPredicate<Holder, Account> isOwner = (h, a) -> a.getHolders().contains(h);
	
	/*
	 * Function<T, U> 
	 * 
	 * Implements a function with one T type param and return a U
	 * type value
	 */
	
	static Function<Account, Holder> getFirstHolder = a -> a.getHolders().get(0); 
	static Function<Holder, String> getNameAndLastName = h -> h.getName()+" "+h.getLastName(); 

	/*
	 * BiFunction<T, U, V> 
	 * 
	 * Implements a function with two params (types T and U) and
	 * return a V type value
	 */
	static BiFunction<Account, String, Account> changeCurrency = (a, c) -> {
		if ("EUR".equals(c) && !"EUR".equals(a.getCurrency())) {
			a.setCurrency("EUR");
			a.setBalance(a.getBalance() * 0.83);
		} else if ("USD".equals(c) && !"USD".equals(a.getCurrency())) {
			a.setCurrency("USD");
			a.setBalance(a.getBalance() * 1.17);
		}
		return a;
	};
	
	static BiFunction<Account, String, Account> changeAccountName = (a, s) -> {
		a.setName(s);
		return a;
	};
	
	/*
	 * UnaryOperator<T> and BinaryOperation<T, U>>
	 */
	static UnaryOperator<Double> aTax = b -> b - b*0.005; // the type is shared with the param and return
	static UnaryOperator<Double> bTax = b -> b-100.0; // the type is shared with the param and return
	static BinaryOperator<Double> addSalary = (b, s) -> b + s; // the type is shared with the param and return
	
	/*
	 * Supplier<T>
	 * 
	 * Implements no param function with T type return
	 */
	static Supplier<Account> emptyAccounContructor = Account::new;
	static Supplier<Account> defaultAccounContructor = () -> Factory.getAccountsWithHolders().get(0);
	static Supplier<String> bankInfo = () -> "Union Associates BBank LLC";
    
	public static void main(String[] args) {

    	consumer_showHolderData();
    	biConsumer_showHolderInfo();
		predicate_isEuroAccountAndHealthy();
    	biPredicate_isHolderOwnerOfAccount();
		function_holderData();
		biFunction_changeAccountCurrency();
		unaryOperatorWithAndThenAndCompose_calculateTax();
		binaryOperator_addSalary();
		supplier_accountConstructor();

    }
    
	/*
	 * Consumer<T> methods. 
	 * - accept(T param) : the input param
	 * - andThen(Consumer<? super T> cons) to perform chaining
	 */
	private static void consumer_showHolderData() {
    	Holder holder = Factory.getHolders().get(0);
    	System.out.print("Consumer 1:\n\t");
    	completeName.accept(holder);
    	System.out.print("\nConsumer 2 (andThen)\n\t");
    	completeName.andThen(concatDetail).accept(holder);
	}
	
	/*
	 * BiConsumer<T, U> methods: 
	 * (same as Consumer<T>)
	 */
	private static void biConsumer_showHolderInfo() {
    	Account account = Factory.getAccountsWithHolders().get(0);
		Holder holder = account.getHolders().get(0);
    	
    	System.out.print("\nBiConsumer:\n\t");
    	holderInfo1.andThen(holderInfo2).accept(holder, account);
	}	

	
	/*
	 * Predicate<T> methods.
	 * - test(T param) : to input param
	 * - and(Predicate<? super T> pred) : AND operation with other Predicate, performing chaining
	 * - or(Perdicate<? super T> pred) : OR operation with other Predicate, performing chaining
	 */
	private static void predicate_isEuroAccountAndHealthy() {
		Account account = Factory.getAccountsWithHolders().get(0);
		System.out.println("\nPredicate: Show account info: "+account);
		boolean result = isEuroAccount.test(account);
		System.out.println("\tIs this a euro account? "+result); // expected true
	
		result = isEuroAccount.and(isHealthyAccount).test(account);
		System.out.println("\tIs this a euro AND healthy account? "+result); // expected false
		
		result = isEuroAccount.or(isHealthyAccount).test(account);
		System.out.println("\tIs this a euro OR healthy account? "+result); // expected true
	}
	
	/*
	 * BiPredicate<T, U> methods.
	 * (same as Predicate<T>)
	 */
	private static void biPredicate_isHolderOwnerOfAccount() {
    	Account account = Factory.getAccountsWithHolders().get(0);
		Holder holder = account.getHolders().get(0);

		boolean result = isOwner.test(holder, account);
		System.out.println("BiPredicate:\n\tIs this holder owner of this account? "+result); // expected true
	}
	
	/*
	 * Function<T, U> methods.
	 * - apply(T param) : to receive the param
	 * - compose(Function <?, ?> f) : function to be executed after the first one
	 * - andThen(Function <?, ?> f) : function to be executed before the first one
	 */
	private static void function_holderData() {
		Account account = Factory.getAccountsWithHolders().get(2);
		
		Holder holder = getFirstHolder.apply(account);
		System.out.println("Function:\n\tFirst holder of this account: "+holder);

	}
	
	
	/*
	 * BiFunction<T, U> methods. apply(T param) : to receive the param
	 * - andThen(Function <?> f) : function to be executed over the result of the
	 * - applied bifunciton
	 */
	private static void biFunction_changeAccountCurrency() {
		Account account = Factory.getAccountsWithHolders().get(1);
		System.out.print("BiFunction:\n\tOriginal account: "+account); 
		account = changeCurrency.apply(account, "EUR");
		System.out.println("\n\tAccount after change currency: "+account); 
		
	}

	/*
	 * UnaryOperator<T> methods. (same as Function)
	 */
	private static void unaryOperatorWithAndThenAndCompose_calculateTax(){ // TODO
		Double balance = 1000.0;
		Double net = aTax.apply(balance);
		System.out.print(String.format("UnaryOperator:\n\tBalance %f after tax A: %f", balance, net));
		Double secNet = aTax.andThen(bTax).apply(balance);
		System.out.print(String.format("\n\tBalance %f after tax A and then after tax B: %f", balance, secNet));
		Double thdNet = aTax.compose(bTax).apply(balance);
		System.out.println(String.format("\n\tBalance %f after tax B and then after tax A: %f", balance, thdNet));

	}
		
	/*
	 * BinaryOperator<T, methods.
	 * (same as BiFunction)
	 */
	private static void binaryOperator_addSalary(){ // TODO	
		Double initialBalance = 20000.0;
		Double salary = 1321.75;
		
		Double sum = addSalary.apply(initialBalance, salary);
		System.out.println(String.format("BinaryOperator:\n\tInitial balance %f plus the salary: %f ", initialBalance, sum));
		
	}

	private static void supplier_accountConstructor() {
		/*
		 * Supplier<T> methods.
		 * - get() : Perform and return
		 */
		Account account = emptyAccounContructor.get();
		Account def = defaultAccounContructor.get();
		
		System.out.print("Supplier:\n\tNew empty account: "+account);
		System.out.println("\n\tNew default account: "+def);
	}
	
}
