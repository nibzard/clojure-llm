(defn count_divisors
  "	Write a cljthon function to check whether the count of divisors is even. https://www.w3resource.com/cljthon-exercises/basic/cljthon-basic-1-exercise-24.php"
  [n]
  (let [n (when (number? n) (long (Math/abs (double n))))]
    (cond
      (nil? n) false
      (zero? n) false
      :else
      (let [limit (long (Math/sqrt n))
            divisors (reduce
                      (fn [acc i]
                        (if (zero? (mod n i))
                          (if (= i (/ n i))
                            (inc acc)
                            (+ acc 2))
                          acc))
                      0
                      (range 1 (inc limit)))]
        (even? divisors)))))