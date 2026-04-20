(defn sumofFactors
  "	Write a cljthon function to find the sum of even factors of a number."
  [n]
  (let [n (if (number? n) (Math/abs (long n)) 0)]
    (cond
      (zero? n) 0
      :else
      (reduce
       +
       (for [i (range 1 (inc (long (Math/sqrt n))))
             :when (zero? (mod n i))
             f (let [q (quot n i)]
                 (if (= i q) [i] [i q]))
             :when (even? f)]
         f)))))