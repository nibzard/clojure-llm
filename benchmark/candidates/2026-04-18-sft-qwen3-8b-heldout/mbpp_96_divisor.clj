(defn divisor
  "	Write a cljthon function to find the number of divisors of a given integer."
  [n]
  (let [n (if (number? n) (long n) 0)]
    (cond
      (nil? n) 0
      (zero? n) 0
      (neg? n) 0
      :else
      (let [limit (long (Math/sqrt n))
            divisors (reduce
                      (fn [cnt d]
                        (if (zero? (mod n d))
                          (if (= d (quot n d))
                            (inc cnt)
                            (+ 2 cnt))
                          cnt))
                      0
                      (range 1 (inc limit)))]
        (if (<= limit (quot n limit))
          (+ 2 divisors)
          (inc divisors))))))