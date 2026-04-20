(defn find_tuples
  "	Write a function to find vectors which have all elements divisible by k from the given list of vectors."
  [test_list K]
  (let [divisible? (fn [n] (zero? (mod n K)))]
    (vec
     (filter (fn [v]
               (and (vector? v)
                    (every? divisible? v)))
             (or test_list [])))))