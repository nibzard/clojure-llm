(defn find_Rotations
  "	Write a cljthon function to find the minimum number of rotations (greater than 0) required to get the same string."
  [str]
  (let [s (or str "")
        n (count s)]
    (if (zero? n)
      0
      (or
       (first
        (keep (fn [k]
                (when (= s (apply str (concat (drop k s) (take k s))))
                  k))
              (range 1 (inc n))))
       0))))