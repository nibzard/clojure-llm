(defn find-rotations
  "Write a cljthon function to find the minimum number of rotations (greater than 0) required to get the same string."
  [s]
  (let [len (count s)]
    (if (<= len 1)
      0
      (loop [i 1]
        (if (= i len)
          0
          (let [rotated (str (subs s i) (subs s 0 i))]
            (if (= s rotated)
              i
              (recur (inc i)))))))))