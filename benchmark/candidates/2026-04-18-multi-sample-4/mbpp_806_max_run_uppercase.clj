(defn max_run_uppercase
  "Write a function to find maximum run of uppercase characters in the given string."
  [test_str]
  (let [s (or test_str "")]
    (loop [chs (seq s)
           current-run 0
           max-run 0]
      (if-let [ch (first chs)]
        (let [next-run (if (Character/isUpperCase ^char ch)
                         (inc current-run)
                         0)]
          (recur (next chs) next-run (max max-run next-run)))
        max-run))))