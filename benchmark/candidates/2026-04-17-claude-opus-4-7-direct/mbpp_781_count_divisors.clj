(defn count_divisors
  "Write a cljthon function to check whether the count of divisors is even."
  [n]
  (if (or (nil? n) (not (integer? n)) (< n 1))
    false
    (let [sqrt-n (Math/sqrt n)]
      (even? (count (filter #(zero? (rem n %)) (range 1 (inc sqrt-n))))))))