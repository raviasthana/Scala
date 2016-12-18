package week3.reactive

object account {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val acct = new BankAccount                      //> acct  : week3.reactive.BankAccount = week3.reactive.BankAccount@39ba5a14
  acct deposit 50
  acct withdraw 20                                //> res0: Int = 30
  acct withdraw 20                                //> res1: Int = 10
  acct withdraw 15                                //> java.lang.Error: insufficient funds
                                                  //| 	at week3.reactive.BankAccount.withdraw(BankAccount.scala:12)
                                                  //| 	at week3.reactive.account$$anonfun$main$1.apply$mcV$sp(week3.reactive.ac
                                                  //| count.scala:9)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.reactive.account$.main(week3.reactive.account.scala:3)
                                                  //| 	at week3.reactive.account.main(week3.reactive.account.scala)
}