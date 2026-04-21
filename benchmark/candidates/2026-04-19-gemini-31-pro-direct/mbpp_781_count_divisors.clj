(defn count_divisors
  "Write a cljthon function to check whether the count of divisors is even."
  [n]
  (let [root (Math/sqrt n)]
    (not (== root (Math/floor root)))))