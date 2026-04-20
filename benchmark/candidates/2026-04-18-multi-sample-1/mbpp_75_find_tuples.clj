(defn find_tuples
  "	Write a function to find vectors which have all elements divisible by k from the given list of vectors."
  [test_list K]
  (let [divisible-by-k? (fn [v]
                          (and (vector? v)
                               (not (zero? K))
                               (every? #(zero? (mod % K)) v)))]
    (vec (filter divisible-by-k? test_list))))