(defn first-Digit
  "Write a cljthon function to find the first digit of a given number."
  [n]
  (when (number? n)
    (let [abs-n (Math/abs (long n))]
      (loop [current abs-n]
        (if (< current 10)
          current
          (recur (quot current 10)))))))