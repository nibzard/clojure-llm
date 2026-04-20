(defn count_divisors
  "	Write a cljthon function to check whether the count of divisors is even. https://www.w3resource.com/cljthon-exercises/basic/cljthon-basic-1-exercise-24.php"
  [n]
  (let [n (when (number? n) (long (Math/abs (double n))))
        limit (when n (long (Math/sqrt (double n))))]
    (cond
      (nil? n) false
      (zero? n) false
      (= n 1) false
      :else
      (let [pairs (for [i (range 1 (inc limit))
                        :when (zero? (mod n i))]
                    (if (= i (/ n i)) 1 2))]
        (even? (reduce + 0 pairs))))))