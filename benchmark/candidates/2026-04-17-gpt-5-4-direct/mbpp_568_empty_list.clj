(defn empty_list
  "Write a function to create a list of N empty dictionaries."
  [length]
  (apply list (repeat (max 0 (or length 0)) {})))