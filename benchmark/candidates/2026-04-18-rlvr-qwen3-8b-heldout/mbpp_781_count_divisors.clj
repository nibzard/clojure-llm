(defn count_divisors
  "	Write a cljthon function to check whether the count of divisors is even. https://www.w3resource.com/cljthon-exercises/basic/cljthon-basic-1-exercise-24.php"
  [n]
  (let [n (if (number? n) (long n) 0)]
    (if (zero? n)
      false
      (let [divs (filter #(zero? (mod n %))
                         (range 1 (inc (long (Math/sqrt n)))))
            total (count divs)
            pair-count (if (= (mod n (long (Math/sqrt n))) 0) (- total 1) total)]
        (even? pair-count)))))