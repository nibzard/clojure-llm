(defn max_run_uppercase
  "	Write a function to find maximum run of uppercase characters in the given string."
  [test_str]
  (let [s (or test_str "")]
    (reduce
      (fn [[max-run curr-run] ch]
        (if (Character/isUpperCase ^char ch)
          (let [next-run (inc curr-run)]
            [(max max-run next-run) next-run])
          [max-run 0]))
      [0 0]
      s)
    (first
      (reduce
        (fn [[max-run curr-run] ch]
          (if (Character/isUpperCase ^char ch)
            (let [next-run (inc curr-run)]
              [(max max-run next-run) next-run])
            [max-run 0]))
        [0 0]
        s))))