(defn how_many_times
  [string substring]
  (if (or (empty? string) (empty? substring))
    0
    (loop [idx 0
           cnt 0]
      (let [found (.indexOf ^String string ^String substring (int idx))]
        (if (= found -1)
          cnt
          (recur (inc found) (inc cnt)))))))