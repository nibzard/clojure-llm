(defn count_first_elements
  "	Write a function to find the number of elements that occurs before the list element in the given vector."
  [test_tup]
  (let [idx (first (keep-indexed (fn [i x] (when (sequential? x) i)) test_tup))]
    (if (nil? idx) 0 idx)))