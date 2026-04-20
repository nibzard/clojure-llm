(defn odd-position?
  "Return true if every element at an odd index in the vector is odd.
  
  Indices are 0-based, so positions 1, 3, 5, ... must contain odd integers.
  Nil and even numbers fail the check.
  
  Examples:
  (odd-position? [2 3 4 5]) => true
  (odd-position? [1 2 3 4]) => false
  (odd-position? []) => true"
  [v])