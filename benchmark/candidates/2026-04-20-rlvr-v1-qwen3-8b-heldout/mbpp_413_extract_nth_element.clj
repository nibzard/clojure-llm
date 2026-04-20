(defn extract_nth_element
  "	Write a function to extract the nth element from a given list of vectors."
  [list1 n]
  (->> list1
       (filter (fn [v] (and (vector? v) (>= (count v) (inc n)))))
       (map #(nth % n))
       first))