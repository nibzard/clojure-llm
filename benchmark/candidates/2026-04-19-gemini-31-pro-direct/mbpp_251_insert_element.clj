(defn insert_element
  "Write a function that takes in a list and an element and inserts the element before each element in the list, and returns the resulting list."
  [list element]
  (mapcat #(vector element %) list))