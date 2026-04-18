(defn extract_nth_element
  "	Write a function to extract the nth element from a given list of vectors."
  [list1 n]
  (when (and (seq list1)
             (integer? n))
    (->> list1
         (filter #(and (vector? %) (>= (count %) (inc n))))
         (map #(nth % n))
         first)))