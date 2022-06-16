package hello.itemservice.validation;

import hello.itemservice.domain.item.Item;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setItemName(" ");
        item.setPrice(0);
        item.setQuantity(10000);

        // validate에서 group 호출 가능
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        for (ConstraintViolation<Item> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation.message = " + violation.getMessage());
        }
    }

    public static class Parent {

        @NotNull
        private String parentName;

        public Parent(String parentName) {
            this.parentName = parentName;
        }

        public Parent() {
        }

        public void method1(){
            System.out.println("Parent.method1");
        }
    }

    public static class Child extends Parent {

        @NotNull
        private String childName;

        public Child(String parentName, String childName) {
            super(parentName);
            this.childName = childName;
        }

        public Child(String childName) {
            this.childName = childName;
        }

        public void method1(){
            System.out.println("Child.method1");
        }
    }

    @Test
    void beanValidateParent() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Child child = new Child("childname");

        Set<ConstraintViolation<Child>> validate = validator.validate(child);

        for (ConstraintViolation<Child> violation : validate) {
            System.out.println("violation = " + violation);
            System.out.println("violation = " + violation.getMessage());
        }
    }


}

