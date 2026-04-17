(defn check_dict_case
  "Given a map, return true if all keys are strings in lower 
  case or all keys are strings in upper case, else return false.
  The function should return false is the given map is empty.
  Examples:
  >>> (check_dict_case {"a" "apple" "b" "banana"})
  true
  >>> (check_dict_case {"a" "apple" "A" "banana" "B" "banana"})
  false
  >>> (check_dict_case {"a" "apple" 8 "banana" "a" "apple"})
  false
  >>> (check_dict_case {"Name" "John" "Age" "36" "City" "Houston"})
  false
  >>> (check_dict_case {"STATE" "NC" "ZIP" "12345"})
  true"
  [dict]
  