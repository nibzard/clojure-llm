(defn find_tuples
  "	Write a function to find vectors which have all elements divisible by k from the given list of vectors."
  [test_list K]
  (if (nil? test_list)
    []
    (vec
     (filter
      (fn [v]
        (and (vector? v)
             (every? #(and (number? %) (zero? (mod % K))) v)))
      test_list))))