(defn how_many_times
  "Find how many times a given substring can be found in the original string. Count overlaping cases."
  [string substring]
  (if (or (empty? string) (empty? substring))
    0
    (loop [s string
           cnt 0]
      (let [idx (clojure.string/index-of s substring)]
        (if (nil? idx)
          cnt
          (recur (subs s (inc idx)) (inc cnt)))))))