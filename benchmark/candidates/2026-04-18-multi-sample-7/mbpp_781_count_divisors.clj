(defn count_divisors
  "	Write a cljthon function to check whether the count of divisors is even. https://www.w3resource.com/cljthon-exercises/basic/cljthon-basic-1-exercise-24.php"
  [n]
  (let [n (when (number? n) (long n))]
    (cond
      (nil? n) false
      (<= n 0) false
      :else (let [root (long (Math/sqrt n))]
              (not= (* root root) n)))))