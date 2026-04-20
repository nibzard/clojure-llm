(defn get_ludic
  "	Write a function to get all lucid numbers smaller than or equal to a given integer."
  [n]
  (let [n (or n 0)]
    (if (< n 1)
      []
      (letfn [(sieve [nums]
                (lazy-seq
                  (when-let [[x & xs] (seq nums)]
                    (cons x
                          (sieve
                           (map second
                                (remove #(zero? (mod (inc (first %)) x))
                                        (map-indexed vector xs))))))))]
        (vec (sieve (range 1 (inc n)))))))