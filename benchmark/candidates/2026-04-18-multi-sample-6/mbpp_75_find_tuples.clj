(defn find_tuples
  "	Write a function to find vectors which have all elements divisible by k from the given list of vectors."
  [test_list K]
  (let [divisible? (fn [n] (and (number? n) (zero? (mod n K))))]
    (->> test_list
         (filter vector?)
         (filter #(every? divisible? %))
         vec)))