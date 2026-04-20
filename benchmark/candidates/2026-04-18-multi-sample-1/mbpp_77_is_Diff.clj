(defn is_Diff
  "	Write a cljthon function to find whether a number is divisible by 11."
  [n]
  (let [s (str (if (number? n) (bigint (if (neg? n) (- n) n)) n))
        digits (map #(- (int %) (int \0)) s)
        diff (reduce + (map * digits (cycle [1 -1])))]
    (zero? (mod diff 11))))