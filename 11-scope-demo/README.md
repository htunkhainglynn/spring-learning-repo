## About session

### Counter and Home is easy to read. We need to set the type of counter in RootConfig.java.

### Cart (All about session scope)

I have 3 controllers.

1)CreatCartController

2)AddItemController

3)ClearCartController

and 1 Model called StringCart

#### CreateCartController create StringCart object and add it to session scope.

create StringCart() object.

```
@ModelAttribute("cart")
StringCart loadCart() {
	return new StringCart();
}
```

set to session scope 

```
@SessionAttributes("cart")
```

#### AddItemController get item name(String) from text box and add them to StringCart which is in session scope.

```
@PostMapping("add-item")
String add(@RequestParam String item, @SessionAttribute StringCart cart) {
	cart.add(item);
	return "cart-view";
}
```

``@SessionAttribute StringCart cart`` is used to extract session object from a request.

#### ClearCartController clear the session.

```
@Controller
@SessionAttributes("cart")
public class ClearCartController {
	@GetMapping("clear-cart")
	String clear(SessionStatus session) {
		session.setComplete();
		return "redirect:/";
	}
}
```

It needs ``@SessionAttributes("cart")`` to use ``String clear(SessionStatus session)`` and to call ``session.setComplete();``. 
