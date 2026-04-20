(defn count_first_elements
  "	Write a function to find the number of elements that occurs before the list element in the given vector."
  [test_tup]
  (let [v (vec test_tup)
        first-el (first (drop-while #(nil? %) (rest v)))]
    (if (nil? first-el)
      0
      (count (take-while #(not= % first-el) v)))))