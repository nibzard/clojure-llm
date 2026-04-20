(defn find_Rotations
  "Write a cljthon function to find the minimum number of rotations (greater than 0) required to get the same string."
  [str]
  (let [s (or str "")
        n (count s)]
    (cond
      (zero? n) 0
      :else
      (let [ss (apply str s s)
            idx (.indexOf ss s 1)]
        (if (= idx n) n idx)))))