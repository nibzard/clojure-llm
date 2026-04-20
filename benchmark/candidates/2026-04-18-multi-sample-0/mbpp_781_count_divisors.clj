(defn count_divisors
  "	Write a cljthon function to check whether the count of divisors is even. https://www.w3resource.com/cljthon-exercises/basic/cljthon-basic-1-exercise-24.php"
  [n]
  (let [n (when (number? n) (long n))]
    (and (pos? n)
         (not
          (loop [i 1]
            (cond
              (> (* i i) n) false
              (= (* i i) n) true
              :else (recur (inc i))))))))