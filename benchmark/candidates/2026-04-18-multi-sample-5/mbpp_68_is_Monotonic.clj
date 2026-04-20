(defn is_Monotonic
  "	Write a cljthon function to check whether the given vector is monotonic or not."
  [A]
  (let [xs (seq A)]
    (or (nil? xs)
        (every? true? (map <= A (rest A)))
        (every? true? (map >= A (rest A))))))