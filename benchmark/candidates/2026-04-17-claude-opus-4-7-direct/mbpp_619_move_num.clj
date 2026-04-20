(defn move_num
  "Write a function to move all the numbers to the end of the given string."
  [test_str]
  (let [non-nums (apply str (re-seq #"[^\d]" test_str))
        nums (apply str (re-seq #"\d" test_str))]
    (str non-nums nums)))