from setuptools import setup, find_packages
setup(name='realMonkey',
        version='1.1',
        description='realMonkey moudle',
        author='wuqiaomin,linyanyan',
        packages = find_packages('realMonkey'),
        package_dir = {'':'realMonkey'}, 
        package_data = {'': ['*.jar'],},
        include_package_data=True,
        )
