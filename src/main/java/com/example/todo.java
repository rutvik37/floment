package com.example;

import java.util.regex.Pattern;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class todo {
    Page page;

    public todo(Page page) {
        this.page = page;
    }

    public void performTodoFlow() throws InterruptedException {

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String futureDate = tomorrow.format(formatter);


        page.waitForTimeout(2000);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Create")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("To Do")).click();

    
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Task Name*")).fill(main.taskName1);
        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Add More \\(Optional\\)$")))
                .click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Description")).fill(main.description1);

        page.locator("input[name='dueDate']").fill(futureDate);

        page.getByTestId("modal").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Create")).click();

        System.out.println("Todo create Done");
        Thread.sleep(3000);

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("To Do")).click();
        Thread.sleep(3000);

        page.locator("button[aria-haspopup='menu']").click();
        Thread.sleep(1000);

        page.getByText("Edit", new Page.GetByTextOptions().setExact(true)).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Task Name*"))
                .fill(main.taskName1 + " update");
        page.locator(".lucide.lucide-chevron-down").first().click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Description"))
                .fill(main.description1 + " update");
        
        page.locator("input[name='dueDate']").fill(futureDate);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Update")).click();
        Thread.sleep(2000);

        System.out.println("Todo update Done");

        page.locator("label span").click();
        System.out.println("Todo complete Done");
        Thread.sleep(2000);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Completed (1)")).click();
        Thread.sleep(3000);
        page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName(main.firstName).setExact(true)).click();

        page.getByText("Sign Out").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes,Sign Out")).click();
        Thread.sleep(2000);

        System.out.println("✅ your email :- " + main.email);
        System.out.println("✅ your password :- " + main.password);
        System.out.println("✅ your first name :- " + main.firstName);
        System.out.println("✅ your last name :- " + main.lastName);
    }

}
