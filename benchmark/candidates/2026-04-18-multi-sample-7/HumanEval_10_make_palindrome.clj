(defn make_palindrome
  " Find the shortest palindrome that begins with a supplied string.
  Algorithm idea is simple:
  - Find the longest postfix of supplied string that is a palindrome.
  - Append to the end of the string reverse of a string prefix that comes before the palindromic suffix.
  >>> (make_palindrome "")
  ""
  >>> (make_palindrome "cat")
  "catac"
  >>> (make_palindrome "cata")
  "catac""
  [string]
  (let [s (or string "")
        n (count s)
        palindrome? (fn [x] (= (seq x) (reverse x)))
        idx (or (first (filter #(palindrome? (subs s % n)) (range n))) n)
        prefix (subs s 0 idx)]
    (str s (apply str (reverse prefix)))))