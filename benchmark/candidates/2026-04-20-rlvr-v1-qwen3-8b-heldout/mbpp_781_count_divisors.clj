(defn count_divisors
  "	Write a cljthon function to check whether the count of divisors is even. https://www.w3resource.com/cljthon-exercises/basic/cljthon-basic-1-exercise-24.php"
  [n]
  (let [n (when (number? n) (long n))]
    (if (or (nil? n) (<= n 0))
      false
      (even? (count (filter #(zero? (mod n %))
                            (range 1 (inc (long (Math/sqrt n))))))))))