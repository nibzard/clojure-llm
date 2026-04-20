(defn common_element
  "	Write a function that takes two lists and returns true if they have at least one common element."
  [list1 list2]
  (let [s2 (set list2)]
    (boolean (some s2 list1))))