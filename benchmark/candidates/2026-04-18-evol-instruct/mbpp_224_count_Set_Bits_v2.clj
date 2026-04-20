(defn count-set-bits-in-binary-string
  "Return the number of set bits (\"1\" characters) in a binary string.

  Accepts a string, an integer, or nil:
  - (count-set-bits-in-binary-string \"10101\") => 3
  - (count-set-bits-in-binary-string 10) => 2   ; uses the integer's binary form
  - (count-set-bits-in-binary-string nil) => 0

  Examples:
  (count-set-bits-in-binary-string \"0\") => 0
  (count-set-bits-in-binary-string \"11110000\") => 4"
  [x]
  (let [s (cond
            (nil? x) ""
            (string? x) x
            (integer? x) (Integer/toBinaryString x)
            :else (str x))]
    (count (filter #{\1} s))))