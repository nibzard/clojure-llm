(defn is_Diff
  "	Write a cljthon function to find whether a number is divisible by 11."
  [n]
  (let [digits (map #(- (int %) 48) (seq (str (Math/abs (long n)))))
        diff   (Math/abs
                (reduce + (map-indexed (fn [idx d]
                                         (if (even? idx) d (- d)))
                                       digits)))]
    (zero? (mod diff 11))))