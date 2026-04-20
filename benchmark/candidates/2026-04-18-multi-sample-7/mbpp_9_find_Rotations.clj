(defn find_Rotations
  "Write a cljthon function to find the minimum number of rotations (greater than 0) required to get the same string."
  [str]
  (let [s (or str "")
        n (count s)]
    (cond
      (zero? n) 0
      :else
      (let [doubled (subs (apply str s s) 1)]
        (or (some (fn [i]
                    (when (= s (subs doubled (dec i) (+ (dec i) n)))
                      i))
                  (range 1 (inc n)))
            n)))))