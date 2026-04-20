(defn count_divisors
  "	Write a cljthon function to check whether the count of divisors is even. https://www.w3resource.com/cljthon-exercises/basic/cljthon-basic-1-exercise-24.php"
  [n]
  (let [n (Math/abs (long n))
        root (long (Math/floor (Math/sqrt n)))]
    (even? (reduce
             (fn [cnt i]
               (if (zero? (mod n i))
                 (if (= i (/ n i)) (inc cnt) (+ cnt 2))
                 cnt))
             0
             (range 1 (inc root))))))