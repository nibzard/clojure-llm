(defn vec_to_mini_roman
  "Given a vector of positive integers, convert each number to its lowercase Roman numeral equivalent.
  Restrictions:
  - Each number is between 1 and 1000 inclusive.
  - Return a vector of strings in the same order.

  Examples:
  >>> (vec_to_mini_roman [19 152 426])
  [\"xix\" \"clii\" \"cdxxvi\"]
  >>> (vec_to_mini_roman [1 4 9 10])
  [\"i\" \"iv\" \"ix\" \"x\"]"
  [nums])