//package ru.job4j.lsp;
//
//public class ViolationsPrinciple {
////--------------------------------------------------------------------------------------//
//
//    // 1) предок не выполняет контракта родителя.
//    private static class Parent {
//        public String write() {
//            return "Parent";
//        }
//    }
//
//    private static class Child extends Parent {
//        @Override
//        public String write() {
//            return null;
//        }
//    }
//
//    public static void toWrite() {
//        Parent person = new Child();
//        person.write().length(); // NullPointerException
//    }
////--------------------------------------------------------------------------------------//
//
//    // 2) условия проверки входных параметров изменились.
//    private static class Cat {
//        public int eat(int food) {
//            if (food > 100) {
//                indigestion();
//            }
//            int energy = (int) (food * 0.7);
//            return energy;
//        }
//    }
//
//    private static class Toyger extends Cat {
//        @Override
//        public int eat(int food) {
//            if (food > 70) {
//                indigestion();
//            }
//            return super.eat(food);
//        }
//    }
//
//    public static void feed() {
//        Cat cat1 = new Cat();
//        Cat cat2 = new Toyger();
//        cat1.eat(80);
//        cat2.eat(80);     // поведение "cat1.eat" и "cat2.eat" будет разным, а должно быть одинаковым.
//    }
////--------------------------------------------------------------------------------------//
//
//    // 3) Постусловие изменилось.
//    private static class Credit {
//        public int repayment(int cash) {
//            remainder = remainder - cash;
//            if (paymentDay > System.currentTimeMillis()) {
//                remainder = remainder * penaltyFee;
//            }
//            return remainder;
//        }
//    }
//
//    private static class CorporateCredit extends Credit {
//        @Override
//        public int repayment(int cash) {
//            remainder = (remainder - cash) * personalTerms;
//            return remainder;
//        }
//    }
//    //--------------------------------------------------------------------------------------//
//}
