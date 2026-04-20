(defn make_palindrome
  "Find the shortest palindrome that begins with a supplied string.
  Algorithm idea is simple:
  - Find the longest postfix of supplied string that is a palindrome.
  - Append to the end of the string reverse of a string prefix that comes before the palindromic suffix.
  >>> (make_palindrome \"\")
  \"\"
  >>> (make_palindrome \"cat\")
  \"catac\"
  >>> (make_palindrome \"cata\")
  \"catac\""
  [string]
  (let [n (count string)
        reverse-str #(apply str (reverse %))
        palindrome? #(= % (reverse-str %))]
    (loop [len n]
      (if (<= len 0)
        (str string (reverse-str string))
        (let [suffix (subs string (- n len))]
          (if (palindrome? suffix)
            (let [prefix (subs string 0 (- n len))]
              (str string (reverse-str prefix)))
            (recur (dec len))))))))