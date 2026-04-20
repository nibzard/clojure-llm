(defn divisible_by_digits
  "	Write a function to find numbers within a given range from startnum ti endnum where every number is divisible by every digit it contains. https://www.w3resource.com/cljthon-exercises/lambda/cljthon-lambda-exercise-24.php"
  [startnum endnum]
  (let [s (or startnum 0)
        e (or endnum -1)
        [from to] (if (<= s e) [s e] [e s])
        divisible-by-own-digits?
        (fn [n]
          (let [digits (map #(Character/digit ^char % 10) (str (Math/abs (long n))))]
            (every? #(and (not (zero? %)) (zero? (mod n %))) digits)))]
    (filter divisible-by-own-digits? (range from (inc to)))))