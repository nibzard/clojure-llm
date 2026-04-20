(defn max_run_uppercase
  "	Write a function to find maximum run of uppercase characters in the given string."
  [test_str]
  (let [s (or test_str "")]
    (loop [chars (seq s)
           current-run 0
           max-run 0]
      (if-let [ch (first chars)]
        (if (Character/isUpperCase ^Character ch)
          (recur (next chars) (inc current-run) (max max-run (inc current-run)))
          (recur (next chars) 0 max-run))
        max-run))))