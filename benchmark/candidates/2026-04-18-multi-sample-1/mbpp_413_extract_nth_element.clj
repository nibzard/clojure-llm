(defn extract_nth_element
  "Write a function to extract the nth element from a given list of vectors."
  [list1 n]
  (when (and (sequential? list1)
             (integer? n)
             (not (neg? n)))
    (mapv #(when (and (vector? %) (< n (count %))) (nth % n)) list1)))