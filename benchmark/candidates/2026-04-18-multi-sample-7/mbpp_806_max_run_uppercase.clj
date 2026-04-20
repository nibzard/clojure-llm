(defn max_run_uppercase
  "	Write a function to find maximum run of uppercase characters in the given string."
  [test_str]
  (let [s (or test_str "")]
    (loop [chars (seq s)
           current-run 0
           max-run 0]
      (if-let [ch (first chars)]
        (if (Character/isUpperCase ^char ch)
          (let [next-run (inc current-run)]
            (recur (next chars) next-run (max max-run next-run)))
          (recur (next chars) 0 max-run))
        max-run))))