(defn multiply
  "Complete the function that takes two integers and returns 
  the product of their unit digits.
  Assume the input is always valid.
  Examples:
  >>> (multiply 148 412)
  16
  >>> (multiply 19 28)
  72
  >>> (multiply 2020 1851)
  0
  >>> (multiply 14 -15)
  20"
  [a b]
  (let [unit-digit-a (mod (long a) 10)
        unit-digit-b (mod (long b) 10)]
    (* unit-digit-a unit-digit-b)))