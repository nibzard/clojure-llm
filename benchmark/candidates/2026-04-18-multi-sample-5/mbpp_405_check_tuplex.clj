(defn check_tuplex
  "Write a function to check whether an element exists within a vector."
  [tuplex tuple1]
  (boolean (some #(= % tuple1) tuplex)))